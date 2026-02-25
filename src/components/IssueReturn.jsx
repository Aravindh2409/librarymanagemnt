import { useState } from "react";

function IssueReturn() {
  const [issueData, setIssueData] = useState({
    studentId: "",
    bookId: "",
    issueDate: "",
    dueDate: ""
  });

  const [returnData, setReturnData] = useState({
    studentId: "",
    bookId: "",
    returnDate: ""
  });

  const handleIssueChange = (e) => {
    setIssueData({ ...issueData, [e.target.name]: e.target.value });
  };

  const handleReturnChange = (e) => {
    setReturnData({ ...returnData, [e.target.name]: e.target.value });
  };

  return (
    <div style={pageStyle}>
      <h1 style={{ marginBottom: "35px" }}>🔄 Issue & Return Management</h1>

      <div style={mainContainer}>

        {/* ISSUE CARD */}
        <div style={issueCard}>
          <h2>📖 Issue Book</h2>

          <Input name="studentId" placeholder="Student ID" value={issueData.studentId} onChange={handleIssueChange} />
          <Input name="bookId" placeholder="Book ID" value={issueData.bookId} onChange={handleIssueChange} />
          <Input name="issueDate" type="date" value={issueData.issueDate} onChange={handleIssueChange} />
          <Input name="dueDate" type="date" value={issueData.dueDate} onChange={handleIssueChange} />

          <button style={issueBtn}>Issue Book</button>
        </div>

        {/* RETURN CARD */}
        <div style={returnCard}>
          <h2>📚 Return Book</h2>

          <Input name="studentId" placeholder="Student ID" value={returnData.studentId} onChange={handleReturnChange} />
          <Input name="bookId" placeholder="Book ID" value={returnData.bookId} onChange={handleReturnChange} />
          <Input name="returnDate" type="date" value={returnData.returnDate} onChange={handleReturnChange} />

          <button style={returnBtn}>Return Book</button>
        </div>

      </div>
    </div>
  );
}

/* -------- INPUT COMPONENT -------- */

function Input({ name, placeholder, value, onChange, type = "text" }) {
  return (
    <input
      name={name}
      placeholder={placeholder}
      value={value}
      onChange={onChange}
      type={type}
      style={inputStyle}
    />
  );
}

/* -------- STYLES -------- */

const pageStyle = {
  animation: "fadeInPage 0.6s ease"
};

const mainContainer = {
  display: "flex",
  gap: "40px",
  alignItems: "stretch"
};

const issueCard = {
  flex: 1,
  background: "linear-gradient(135deg,#667eea,#764ba2)",
  padding: "35px",
  borderRadius: "30px",
  color: "white",
  display: "flex",
  flexDirection: "column",
  gap: "18px",
  boxShadow: "0 20px 40px rgba(0,0,0,0.2)"
};

const returnCard = {
  flex: 1,
  background: "linear-gradient(135deg,#ff758c,#ff7eb3)",
  padding: "35px",
  borderRadius: "30px",
  color: "white",
  display: "flex",
  flexDirection: "column",
  gap: "18px",
  boxShadow: "0 20px 40px rgba(0,0,0,0.2)"
};

const inputStyle = {
  padding: "14px 18px",
  borderRadius: "15px",
  border: "none",
  fontSize: "14px",
  outline: "none"
};

const issueBtn = {
  marginTop: "10px",
  padding: "12px",
  borderRadius: "15px",
  border: "none",
  background: "white",
  color: "#764ba2",
  fontWeight: "bold",
  cursor: "pointer",
  transition: "0.3s"
};

const returnBtn = {
  marginTop: "10px",
  padding: "12px",
  borderRadius: "15px",
  border: "none",
  background: "white",
  color: "#ff4b2b",
  fontWeight: "bold",
  cursor: "pointer",
  transition: "0.3s"
};

export default IssueReturn;