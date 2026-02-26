import { useState } from "react";

function Filter() {
  const [filters, setFilters] = useState({
    bookId: "",
    studentId: "",
    genre: "",
    branch: "",
    copies: "",
    year: "",
    overdue: ""
  });

  const handleChange = (e) => {
    setFilters({ ...filters, [e.target.name]: e.target.value });
  };

  const resetFilters = () => {
    setFilters({
      bookId: "",
      studentId: "",
      genre: "",
      branch: "",
      copies: "",
      year: "",
      overdue: ""
    });
  };

  return (
    <div style={pageStyle}>
      <h1 style={{ marginBottom: "30px" }}>🔍 Advanced Book Filtering</h1>

      <div style={filterCard}>
        <div style={gridLayout}>

          <Input name="bookId" placeholder="Book ID" value={filters.bookId} onChange={handleChange} />
          <Input name="studentId" placeholder="Student ID" value={filters.studentId} onChange={handleChange} />

          <Select name="genre" value={filters.genre} onChange={handleChange}
            options={["Tech", "Fiction", "Science", "History"]} placeholder="Select Genre" />

          <Select name="branch" value={filters.branch} onChange={handleChange}
            options={["CSE", "ECE", "MECH", "CIVIL"]} placeholder="Select Branch" />

          <Input name="copies" placeholder="No of Copies" value={filters.copies} onChange={handleChange} type="number" />
          <Input name="year" placeholder="Published Year" value={filters.year} onChange={handleChange} type="number" />

          <Select name="overdue" value={filters.overdue} onChange={handleChange}
            options={["Yes", "No"]} placeholder="Overdue Status" />

        </div>

        <div style={buttonRow}>
          <button style={applyBtn}>Apply Filters</button>
          <button style={resetBtn} onClick={resetFilters}>Reset</button>
        </div>
      </div>
    </div>
  );
}

/* -------- COMPONENTS -------- */

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

function Select({ name, value, onChange, options, placeholder }) {
  return (
    <select
      name={name}
      value={value}
      onChange={onChange}
      style={inputStyle}
    >
      <option value="">{placeholder}</option>
      {options.map((opt, index) => (
        <option key={index} value={opt}>{opt}</option>
      ))}
    </select>
  );
}

/* -------- STYLES -------- */

const pageStyle = {
  animation: "fadeInPage 0.6s ease"
};

const filterCard = {
  background: "rgba(255,255,255,0.9)",
  padding: "35px",
  borderRadius: "30px",
  boxShadow: "0 20px 40px rgba(0,0,0,0.1)",
  backdropFilter: "blur(20px)"
};

const gridLayout = {
  display: "grid",
  gridTemplateColumns: "repeat(3, 1fr)",
  gap: "25px"
};

const inputStyle = {
  padding: "14px 18px",
  borderRadius: "15px",
  border: "1px solid #ddd",
  fontSize: "14px",
  outline: "none",
  transition: "0.3s"
};

const buttonRow = {
  marginTop: "30px",
  display: "flex",
  gap: "20px",
  justifyContent: "flex-end"
};

const applyBtn = {
  padding: "12px 30px",
  borderRadius: "15px",
  border: "none",
  background: "linear-gradient(45deg,#00f2fe,#4facfe)",
  color: "white",
  fontWeight: "bold",
  cursor: "pointer",
  boxShadow: "0 10px 25px rgba(0,0,0,0.2)"
};

const resetBtn = {
  padding: "12px 30px",
  borderRadius: "15px",
  border: "none",
  background: "linear-gradient(45deg,#ff416c,#ff4b2b)",
  color: "white",
  fontWeight: "bold",
  cursor: "pointer"
};

export default Filter;