import { Link } from "react-router-dom";
import NTI from "../assets/NTI-Logo.webp";
import Menu from "../assets/menu.webp";
import Close from "../assets/close.webp";
import { useState } from "react";
function Navbar() {
  const [isMenuOpen, setIsMenuOpen] = useState<boolean>(false);

  return (
    <nav className="flex items-center justify-around h-28 bg-(--border-color) overflow-hidden">
      <Link className="w-16 sm:w-16 md:w-20 lg:w-24" to="about">
        <img className="rounded-full " src={NTI} alt="NTI Logo" />
      </Link>

      <button
        className="inline sm:inline md:hidden lg:hidden p-[0.5rem_1rem] text-(--text-color) text-2xl bg-(--primary-color) border-2 border-b-6 border-(--secondary-color) rounded"
        onClick={() => setIsMenuOpen(!isMenuOpen)}
      >
        {isMenuOpen ? (
          <img src={Close} alt="menu-image" />
        ) : (
          <img src={Menu} alt="close-image" />
        )}
      </button>

      <div
        className={` ${isMenuOpen ? "flex" : "hidden"}
           sm:absolute sm:right-0 sm:top-0 sm:bg-(--secondary-color) sm:flex-col sm:h-full sm:float-right sm:border-0 sm: text-(--text-color) text-2xl [&>.child]:bg-(--primary-color) [&>.child]:border-2 [&>.child]:border-b-6 [&>.child]:border-(--secondary-color) [&>.child]:font-black [&>.child] [&>.child]:rounded`}
      >
        <Link className="child" to="/guide">
          Guide
        </Link>

        <Link className="child" to="/">
          Scores
        </Link>

        <Link className="child" to="/about">
          About
        </Link>
      </div>
    </nav>
  );
}

export default Navbar;
