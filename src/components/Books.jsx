import { useState } from "react";

function Books() {
  const [books, setBooks] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [error, setError] = useState("");

  const [newBook, setNewBook] = useState({
    bookId: "",
    bookName: "",
    bookGenre: "",
    bookAuthor: "",
    publishedYear: "",
    totalCopies: "",
    availableCopies: ""
  });

  const handleChange = (e) => {
    setNewBook({ ...newBook, [e.target.name]: e.target.value });
  };

  const handleAddBook = () => {
    // Check if any field is empty
    const isEmpty = Object.values(newBook).some(
      (value) => value.trim() === ""
    );

    if (isEmpty) {
      setError("⚠️ All fields are mandatory!");
      return;
    }

    setError("");
    setBooks([...books, newBook]);
    setShowModal(false);

    setNewBook({
      bookId: "",
      bookName: "",
      bookGenre: "",
      bookAuthor: "",
      publishedYear: "",
      totalCopies: "",
      availableCopies: ""
    });
  };

  const deleteBook = (id) => {
    setBooks(books.filter(b => b.bookId !== id));
  };

  return (
    <div style={pageStyle}>
      <h1 style={{ marginBottom: "25px" }}>📖 Books Management</h1>

      {/* Top Controls */}
      <div style={topBar}>
        <input placeholder="🔍 Search books..." style={searchStyle} />
        <button style={addBtn} onClick={() => setShowModal(true)}>
          + Add Book
        </button>
      </div>

      {/* Table */}
      <div style={tableContainer}>
        <table style={tableStyle}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Genre</th>
              <th>Author</th>
              <th>Year</th>
              <th>Total</th>
              <th>Available</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {books.map((book, index) => (
              <tr key={index} style={rowStyle}>
                <td>{book.bookId}</td>
                <td>{book.bookName}</td>
                <td>{book.bookGenre}</td>
                <td>{book.bookAuthor}</td>
                <td>{book.publishedYear}</td>
                <td>{book.totalCopies}</td>
                <td>{book.availableCopies}</td>
                <td>
                  <button style={deleteBtn} onClick={() => deleteBook(book.bookId)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal */}
      {showModal && (
        <div style={modalOverlay}>
          <div style={modalBox}>
            <h2>Add New Book</h2>

            <input name="bookId" placeholder="Book ID" value={newBook.bookId} onChange={handleChange} style={inputStyle} />
            <input name="bookName" placeholder="Book Name" value={newBook.bookName} onChange={handleChange} style={inputStyle} />
            <input name="bookGenre" placeholder="Book Genre" value={newBook.bookGenre} onChange={handleChange} style={inputStyle} />
            <input name="bookAuthor" placeholder="Book Author" value={newBook.bookAuthor} onChange={handleChange} style={inputStyle} />
            <input name="publishedYear" placeholder="Published Year" value={newBook.publishedYear} onChange={handleChange} style={inputStyle} />
            <input name="totalCopies" placeholder="Total Copies" value={newBook.totalCopies} onChange={handleChange} style={inputStyle} />
            <input name="availableCopies" placeholder="Available Copies" value={newBook.availableCopies} onChange={handleChange} style={inputStyle} />

            {error && (
              <p style={{ color: "red", fontWeight: "bold" }}>{error}</p>
            )}

            <div style={{ display: "flex", gap: "15px", marginTop: "10px" }}>
              <button style={saveBtn} onClick={handleAddBook}>
                Save
              </button>
              <button style={cancelBtn} onClick={() => setShowModal(false)}>
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

/* -------- STYLES -------- */

const pageStyle = {
  animation: "fadeInPage 0.6s ease"
};

const topBar = {
  display: "flex",
  justifyContent: "space-between",
  marginBottom: "20px"
};

const searchStyle = {
  padding: "12px 15px",
  borderRadius: "15px",
  border: "none",
  width: "300px"
};

const addBtn = {
  padding: "12px 25px",
  borderRadius: "15px",
  border: "none",
  background: "linear-gradient(45deg,#ff00cc,#3333ff)",
  color: "white",
  fontWeight: "bold",
  cursor: "pointer"
};

const tableContainer = {
  background: "rgba(255,255,255,0.9)",
  padding: "20px",
  borderRadius: "25px"
};

const tableStyle = {
  width: "100%",
  borderCollapse: "collapse",
  textAlign: "center"
};

const rowStyle = {
  padding: "10px"
};

const deleteBtn = {
  padding: "6px 12px",
  borderRadius: "10px",
  border: "none",
  background: "#ff416c",
  color: "white",
  cursor: "pointer"
};

const modalOverlay = {
  position: "fixed",
  top: 0,
  left: 0,
  width: "100%",
  height: "100%",
  background: "rgba(0,0,0,0.5)",
  display: "flex",
  justifyContent: "center",
  alignItems: "center"
};

const modalBox = {
  background: "white",
  padding: "30px",
  borderRadius: "25px",
  width: "400px",
  display: "flex",
  flexDirection: "column",
  gap: "12px"
};

const inputStyle = {
  padding: "10px",
  borderRadius: "12px",
  border: "1px solid #ddd"
};

const saveBtn = {
  flex: 1,
  padding: "10px",
  borderRadius: "12px",
  border: "none",
  background: "#43e97b",
  color: "white",
  fontWeight: "bold",
  cursor: "pointer"
};

const cancelBtn = {
  flex: 1,
  padding: "10px",
  borderRadius: "12px",
  border: "none",
  background: "#ff416c",
  color: "white",
  fontWeight: "bold",
  cursor: "pointer"
};

export default Books;