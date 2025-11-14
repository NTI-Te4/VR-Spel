import { Link } from "react-router-dom";
import NTI from "../assets/img/NTI-Logo.webp";
import Menu from "../assets/img/menu.webp";
import Close from "../assets/img/close.webp";
import { useState } from "react";

function Navbar() {
  const [isMenuOpen, setIsMenuOpen] = useState<boolean>(false);

  return (
    <nav className="flex items-center justify-around h-28 px-4 bg-(--panel-color) relative z-50">
      <Link className="w-16 sm:w-16 md:w-18 lg:w-20" to="/about">
        <img className="rounded-full cursor-pointer" src={NTI} alt="NTI Logo" />
      </Link>

      {/* Navbar menu */}
      <div className="hidden md:flex justify-between w-2/3 h-auto [&>.nav-child]:border-2 [&>.nav-child]:border-b-4 [&>.nav-child]:border-(--secondary-color) [&>.nav-child]:w-1/5 [&>.nav-child]:py-1 [&>.nav-child]:text-center text-(--text-color) text-2xl font-black [&>.nav-child]:rounded [&>.nav-child]:bg-(--primary-color)  [&>.nav-child]:transition-opacity [&>.nav-child]:duration-300 [&>.nav-child]:hover:opacity-85">
        <Link className="nav-child" to="/guide">
          Guide
        </Link>
        <Link className="nav-child" to="/">
          Scores
        </Link>
        <Link className="nav-child" to="/about">
          About
        </Link>
      </div>

      {/* Hamburger button */}
      <button
        className="md:hidden p-[0.4rem_1.2rem] bg-(--primary-color) text-(--text-color) border-[2px_2px_4px_2px] border-(--secondary-color) rounded hover:opacity-95 cursor-pointer active:border-b-2"
        onClick={() => setIsMenuOpen(!isMenuOpen)}
      >
        <img src={isMenuOpen ? Close : Menu} alt="menu toggle" />
      </button>

      {/* Overlay */}
      {isMenuOpen && (
        <div
          className="fixed inset-0 bg-black opacity-50 z-40"
          onClick={() => setIsMenuOpen(false)}
        />
      )}

      {/* Mobile sidebar menu */}
      <div
        className={`fixed top-0 right-0 h-full w-56 rounded-l-[10px] bg-(--primary-color) flex flex-col gap-8 p-4 transition-transform duration-300 z-50 [&>.mob-child]:border-3 [&>.mob-child]:border-(--secondary-color) [&>.mob-child]:font-black [&>.mob-child]:text-(--text-color) [&>.mob-child]:text-2xl [&>.mob-child]:w-full [&>.mob-child]:hover:bg-(--secondary-color) [&>.mob-child]:rounded [&>.mob-child]:indent-2 [&>.mob-child]:transition-all [&>.mob-child]:duration-300 ${
          isMenuOpen ? "translate-x-0" : "translate-x-full"
        }`}
      >
        <Link
          className="mob-child"
          to="/guide"
          onClick={() => setIsMenuOpen(false)}
        >
          Guide
        </Link>
        <Link className="mob-child" to="/" onClick={() => setIsMenuOpen(false)}>
          Scores
        </Link>
        <Link
          className="mob-child"
          to="/about"
          onClick={() => setIsMenuOpen(false)}
        >
          About
        </Link>
      </div>
    </nav>
  );
}

export default Navbar;
