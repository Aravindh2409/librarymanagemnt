import { Link, useLocation } from "react-router-dom";
import { useState } from "react";

function Sidebar() {
  const location = useLocation();
  const [isOpen, setIsOpen] = useState(false);
  const [hovered, setHovered] = useState(null);

  const menuItems = [
    { name: "Dashboard", path: "/", icon: "🏠" },
    { name: "Books", path: "/books", icon: "📖" },
    { name: "Filter", path: "/filter", icon: "🔍" },
    { name: "Issue / Return", path: "/issue", icon: "🔄" }
  ];

  return (
    <div
      style={{
        ...sidebarStyle,
        width: isOpen ? "280px" : "95px"
      }}
    >
      {/* Floating Neon Blobs */}
      <div style={blob1}></div>
      <div style={blob2}></div>

      {/* Toggle */}
      <div
        style={{
          ...menuButton,
          transform: isOpen ? "rotate(90deg)" : "rotate(0deg)"
        }}
        onClick={() => setIsOpen(!isOpen)}
      >
        ☰
      </div>

      {/* Logo */}
      <div style={logoStyle}>
        {isOpen ? "📚 LMS" : "📚"}
        </div>

      {/* Menu Items */}
      <div style={{ display: "flex", flexDirection: "column", gap: "22px" }}>
        {menuItems.map((item, index) => {
          const isActive = location.pathname === item.path;

          return (
            <Link
              key={index}
              to={item.path}
              onMouseEnter={() => setHovered(index)}
              onMouseLeave={() => setHovered(null)}
              style={{
                ...menuStyle,
                ...(isActive ? activeStyle : {}),
                ...(hovered === index ? magneticHover : {})
              }}
            >
              <span style={{
                ...iconStyle,
                transform: hovered === index ? "scale(1.3) rotate(8deg)" : "scale(1)"
              }}>
                {item.icon}
              </span>

              {isOpen && <span>{item.name}</span>}
            </Link>
          );
        })}
      </div>
    </div>
  );
}

/* ---------- ULTRA STYLES ---------- */

const sidebarStyle = {
  minHeight: "100vh",
  padding: "35px 20px",
  display: "flex",
  flexDirection: "column",
  gap: "45px",
  position: "relative",
  overflow: "hidden",
  transition: "all 0.5s cubic-bezier(.68,-0.55,.27,1.55)",
  background: "linear-gradient(120deg, #ff00cc, #3333ff, #00f2fe)",
  backgroundSize: "300% 300%",
  animation: "gradientShift 10s ease infinite",
  backdropFilter: "blur(30px)",
  boxShadow: "15px 0 60px rgba(0,0,0,0.4)",
  color: "white"
};

const blob1 = {
  position: "absolute",
  width: "260px",
  height: "260px",
  background: "rgba(255,255,255,0.35)",
  borderRadius: "50%",
  top: "-80px",
  right: "-70px",
  filter: "blur(50px)",
  animation: "floatUltra 8s ease-in-out infinite"
};

const blob2 = {
  position: "absolute",
  width: "220px",
  height: "220px",
  background: "rgba(255,255,255,0.25)",
  borderRadius: "50%",
  bottom: "-60px",
  left: "-60px",
  filter: "blur(45px)",
  animation: "floatUltra 9s ease-in-out infinite"
};

const menuButton = {
  fontSize: "22px",
  cursor: "pointer",
  background: "rgba(255,255,255,0.25)",
  padding: "12px",
  borderRadius: "16px",
  width: "50px",
  textAlign: "center",
  transition: "all 0.4s ease",
  backdropFilter: "blur(15px)"
};

const logoStyle = {
  fontSize: "24px",
  fontWeight: "bold",
  letterSpacing: "1px"
};

const menuStyle = {
  textDecoration: "none",
  color: "white",
  padding: "16px",
  borderRadius: "18px",
  display: "flex",
  alignItems: "center",
  gap: "16px",
  fontWeight: "600",
  transition: "all 0.3s ease",
  background: "rgba(255,255,255,0.12)",
  backdropFilter: "blur(15px)"
};

const magneticHover = {
  transform: "translateX(10px) scale(1.05)",
  boxShadow: "0 10px 30px rgba(255,255,255,0.6)"
};

const iconStyle = {
  fontSize: "22px",
  transition: "all 0.3s ease"
};

const activeStyle = {
  background: "white",
  color: "#ff00cc",
  boxShadow: "0 0 35px rgba(255,255,255,0.9)"
};

export default Sidebar;