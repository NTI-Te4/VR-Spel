import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";

function App() {

  return (
    <BrowserRouter>

      {/* Navigation */}
      <Navbar /> 

      {/* Routes */}
      <Routes>
        <Route path="/guide" element={""} />
        <Route path="/score" element={"0"} />
        <Route path="/about" element={""} />
      </Routes>
    
    </BrowserRouter>
  )
}

export default App;
