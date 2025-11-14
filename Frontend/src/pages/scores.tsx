import Badge from "../assets/img/badge.webp";
import Magnifier from "../assets/img/magnifier.webp";
import Board from "../component/board";

function Scores() {
  return (
    <div>
      <header className="flex justify-center items-center gap-10  text-4xl sm:text-4xl md:text-5xl lg:text-8xl font-black w-fit m-auto mt-5 text-(--text-color) [&>.badge]:w-12 [&>.badge]:sm:w-10 [&>.badge]:md:w-14 [&>.badge]:lg:w-18 ">
        <img className="badge" src={Badge} alt="badge" />
        <h1>Leaderboard</h1>
        <img className="badge" src={Badge} alt="badge" />
      </header>

      <main className="">
        <Board />
      </main>

      <form>
        {/* CHANGE THE MAGNIFIER IMAGE, STYLING DONT WORK ON IT */}
        <img className="w-12" src={Magnifier} alt="magnifier" /> 
        <input type="text" placeholder={""} />
        <button>{}</button>
      </form>

      <footer>

      </footer>
    </div>
  );
}

export default Scores;
