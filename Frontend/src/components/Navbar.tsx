import { Link } from "react-router-dom";

function Navbar() {
    return (
        <nav>
            <Link to="/guide">Guide</Link>

            <Link to="/">Scores</Link>

            <Link to="/about">About</Link>
        </nav>
    )
}

export default Navbar;