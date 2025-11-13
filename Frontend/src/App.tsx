import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Scores from "./pages/scores";

function App() {
  return (
    <BrowserRouter>
      {/* Navigation */}
      <Navbar />

      {/* Routes */}
      <Routes>
        <Route path="/guide" element={""} />
        <Route path="/" element={<Scores />} />
        <Route path="/about" element={""} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
