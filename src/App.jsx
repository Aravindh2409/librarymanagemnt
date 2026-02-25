import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import Dashboard from "./components/Dashboard";
import Books from "./components/Books";
import Filter from "./components/Filter";
import IssueReturn from "./components/IssueReturn";

function App() {
  return (
    <Router>
      <div style={appContainer}>
        <Sidebar />

        <div style={contentContainer}>
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/books" element={<Books />} />
            <Route path="/filter" element={<Filter />} />
            <Route path="/issue" element={<IssueReturn />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

const appContainer = {
  display: "flex",
  minHeight: "100vh"   // makes layout full screen height
};

const contentContainer = {
  flex: 1,
  padding: "30px",
  background: "linear-gradient(135deg, #e0f7fa, #f3e5f5)"
};

export default App;