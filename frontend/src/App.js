import React, { useState } from 'react';
import './App.css';

// Base URLs configuration
const BASE_URL = window.location.origin; // Tự động lấy protocol + host + port
const API_BASE_URL = `${BASE_URL}/api`;
const CHART_BASE_URL = `${BASE_URL}/api/reports`;

function App() {
  const [customerId, setCustomerId] = useState('');
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [showTransactions, setShowTransactions] = useState(false);
  const [customerInfo, setCustomerInfo] = useState(null);

  const fetchCustomerInfo = async (id) => {
    try {
      const response = await fetch(`${API_BASE_URL}/customers/${id}`);
      if (response.ok) {
        const data = await response.json();
        setCustomerInfo(data);
        return true;
      } else {
        setCustomerInfo(null);
        return false;
      }
    } catch (err) {
      console.error('Error fetching customer info:', err);
      setCustomerInfo(null);
      return false;
    }
  };

  const fetchTransactions = async (id) => {
    try {
      const response = await fetch(`${API_BASE_URL}/transactions/customer/${id}`);
      if (response.ok) {
        const data = await response.json();
        setTransactions(data);
        return true;
      } else {
        setTransactions([]);
        return false;
      }
    } catch (err) {
      console.error('Error fetching transactions:', err);
      setTransactions([]);
      return false;
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!customerId.trim()) {
      setError('Vui lòng nhập ID khách hàng');
      return;
    }

    setLoading(true);
    setError('');
    setShowTransactions(false);

    // Kiểm tra xem customer có tồn tại không
    const customerExists = await fetchCustomerInfo(customerId);
    
    if (!customerExists) {
      setError('Không tìm thấy khách hàng với ID này');
      setLoading(false);
      return;
    }

    // Lấy danh sách giao dịch
    const transactionsFound = await fetchTransactions(customerId);
    
    if (transactionsFound) {
      setShowTransactions(true);
    } else {
      setError('Không thể lấy dữ liệu giao dịch');
    }

    setLoading(false);
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleString('vi-VN');
  };

  const formatAmount = (amount) => {
    return new Intl.NumberFormat('vi-VN', {
      style: 'currency',
      currency: 'VND'
    }).format(amount);
  };

  const handleReset = () => {
    setCustomerId('');
    setTransactions([]);
    setError('');
    setShowTransactions(false);
    setCustomerInfo(null);
  };

  return (
    <div className="App">
      <div className="container">
        <h1>Hệ thống Tra cứu Giao dịch VBBank</h1>
        
        {!showTransactions ? (
          <div className="search-form">
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label htmlFor="customerId">ID Khách hàng:</label>
                <input
                  type="number"
                  id="customerId"
                  value={customerId}
                  onChange={(e) => setCustomerId(e.target.value)}
                  placeholder="Nhập ID khách hàng"
                  required
                />
              </div>
              
              <button type="submit" disabled={loading}>
                {loading ? 'Đang tìm kiếm...' : 'Tra cứu Giao dịch'}
              </button>
            </form>
            
            {error && <div className="error-message">{error}</div>}
          </div>
        ) : (
          <div className="results-section">
            <div className="customer-info">
              <h2>Thông tin Khách hàng</h2>
              {customerInfo && (
                <div className="customer-details">
                  <p><strong>ID:</strong> {customerInfo.customerId}</p>
                  <p><strong>Tên:</strong> {customerInfo.firstName} {customerInfo.lastName}</p>
                  <p><strong>Email:</strong> {customerInfo.email}</p>
                  <p><strong>Số điện thoại:</strong> {customerInfo.phoneNumber}</p>
                  <p><strong>Trạng thái:</strong> {customerInfo.status}</p>
                </div>
              )}
            </div>

            <div className="transactions-section">
              <h2>Danh sách Giao dịch ({transactions.length} giao dịch)</h2>
              
              {transactions.length === 0 ? (
                <div className="no-transactions">
                  <p>Khách hàng này chưa có giao dịch nào.</p>
                </div>
              ) : (
                <div className="transactions-table">
                  <table>
                    <thead>
                      <tr>
                        <th>ID</th>
                        <th>Loại giao dịch</th>
                        <th>Số tiền</th>
                        <th>Ngày giao dịch</th>
                        <th>Phương thức</th>
                        <th>Mô tả</th>
                        <th>Trạng thái</th>
                      </tr>
                    </thead>
                    <tbody>
                      {transactions.map((transaction) => (
                        <tr key={transaction.transactionId}>
                          <td>{transaction.transactionId}</td>
                          <td>{transaction.transactionType}</td>
                          <td className={transaction.transactionType === 'DEPOSIT' ? 'amount-positive' : 'amount-negative'}>
                            {formatAmount(transaction.amount)}
                          </td>
                          <td>{formatDate(transaction.transactionDate)}</td>
                          <td>{transaction.transactionMethod}</td>
                          <td>{transaction.description}</td>
                          <td>
                            <span className="status-badge">Hoàn thành</span>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              )}
            </div>

            <div className="chart-section">
              <h2>Biểu đồ Báo cáo Khách hàng</h2>
              <div className="chart-container">
                <img 
                  src={`${CHART_BASE_URL}/customer/${customerId}/chart`}
                  alt={`Biểu đồ báo cáo khách hàng ${customerId}`}
                  className="customer-chart"
                  onError={(e) => {
                    e.target.style.display = 'none';
                    e.target.nextSibling.style.display = 'block';
                  }}
                />
                <div className="chart-placeholder" style={{display: 'none'}}>
                  <p>Không thể tải biểu đồ báo cáo</p>
                  <p>API endpoint: /api/reports/customer/{customerId}/chart</p>
                </div>
              </div>
            </div>

            <div className="actions">
              <button onClick={handleReset} className="reset-btn">
                Tra cứu khách hàng khác
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
