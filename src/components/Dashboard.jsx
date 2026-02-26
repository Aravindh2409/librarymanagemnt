import {
  PieChart,
  Pie,
  Cell,
  Tooltip,
  Legend,
  ResponsiveContainer
} from "recharts";
import { useEffect, useState } from "react";

function Dashboard() {
  const totalBooks = 120;
  const issuedBooks = 45;
  const availableBooks = totalBooks - issuedBooks;

  const [countTotal, setCountTotal] = useState(0);
  const [countIssued, setCountIssued] = useState(0);
  const [countAvailable, setCountAvailable] = useState(0);

  // Animated counters
  useEffect(() => {
    animateCounter(totalBooks, setCountTotal);
    animateCounter(issuedBooks, setCountIssued);
    animateCounter(availableBooks, setCountAvailable);
  }, []);

  const data = [
    { name: "Available", value: availableBooks },
    { name: "Issued", value: issuedBooks }
  ];

  const COLORS = ["#00f2fe", "#ff00cc"];

  return (
    <div style={pageStyle}>
      <h1 style={titleStyle}>📊 Dashboard</h1>

      <div style={mainContainer}>

        {/* LEFT SIDE - STATS */}
        <div style={leftSection}>
          <StatCard title="Total Books" value={countTotal} gradient="linear-gradient(135deg,#667eea,#764ba2)" />
          <StatCard title="Issued Books" value={countIssued} gradient="linear-gradient(135deg,#ff758c,#ff7eb3)" />
          <StatCard title="Available Books" value={countAvailable} gradient="linear-gradient(135deg,#43e97b,#38f9d7)" />
        </div>

        {/* RIGHT SIDE - DONUT CHART */}
        <div style={chartContainer}>
          <h2 style={{ textAlign: "center", marginBottom: "20px" }}>
            Books Distribution
          </h2>

          <ResponsiveContainer width="100%" height={350}>
            <PieChart>
              <Pie
                data={data}
                cx="50%"
                cy="50%"
                innerRadius={90}
                outerRadius={130}
                dataKey="value"
                label
              >
                {data.map((entry, index) => (
                  <Cell key={index} fill={COLORS[index]} />
                ))}
              </Pie>

              <Tooltip />
              <Legend />
            </PieChart>
          </ResponsiveContainer>
        </div>

      </div>
    </div>
  );
}

/* --------- ANIMATED COUNTER FUNCTION --------- */

function animateCounter(target, setter) {
  let start = 0;
  const duration = 800;
  const increment = target / (duration / 16);

  const counter = setInterval(() => {
    start += increment;
    if (start >= target) {
      setter(target);
      clearInterval(counter);
    } else {
      setter(Math.floor(start));
    }
  }, 16);
}

/* --------- STAT CARD --------- */

function StatCard({ title, value, gradient }) {
  return (
    <div style={{
      background: gradient,
      padding: "30px",
      borderRadius: "25px",
      color: "white",
      boxShadow: "0 20px 40px rgba(0,0,0,0.2)",
      backdropFilter: "blur(20px)",
      transition: "transform 0.3s ease",
    }}>
      <h3>{title}</h3>
      <h1 style={{ fontSize: "50px", margin: 0 }}>{value}</h1>
    </div>
  );
}

/* --------- STYLES --------- */

const pageStyle = {
  minHeight: "100vh",
  animation: "fadeInPage 0.6s ease"
};

const titleStyle = {
  marginBottom: "35px"
};

const mainContainer = {
  display: "flex",
  gap: "40px",
  alignItems: "stretch"
};

const leftSection = {
  flex: 1,
  display: "flex",
  flexDirection: "column",
  gap: "30px"
};

const chartContainer = {
  flex: 1.3,
  background: "rgba(255,255,255,0.9)",
  padding: "30px",
  borderRadius: "25px",
  boxShadow: "0 20px 40px rgba(0,0,0,0.1)",
  backdropFilter: "blur(20px)"
};

export default Dashboard;