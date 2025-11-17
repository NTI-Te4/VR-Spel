import Badge from "../assets/img/badge.webp";
import Magnifier from "../assets/img/magnifier.webp";
import Board, { type BoardEntry } from "../component/board";
import TextData from "../assets/json/scorePage.json";
import { useEffect, useState, type FormEvent } from "react";
import cleanInput from "../component/cleanInput";
import axios from "axios";

function Scores() {
  const [search, setSearch] = useState<string>("");
  const [board, setBoards] = useState<BoardEntry[]>([]);
  

  useEffect(() => {
    const fetchScores = async () => {
      try {
        const res = search
        ? await axios.get(`/api/score/getRankedScore/${search}`)
        : await axios.get('/api/score/getRankedScores');
      setBoards(res.data);

      } catch (err) {
        console.log(err);
      }
    }

    fetchScores();
  }, [search]);

  const boardMapper = () =>
    board.map((entry, index) => (
      <Board
        bgColor="bg-(--secondary-color)"
        key={index}
        rank={`#${entry.rank}`}
        name={entry.username}
        score={entry.score}
        date={entry.date.slice(0,10)}
        time={entry.date.slice(11,16)}
      />
    ));

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
  };

  return (
    <div className=" flex flex-col justify-around items-center h-screen overflow-auto">
      <header className="flex gap-4 text-3xl sm:text-4xl md:text-5xl lg:text-6xl xl:text-7xl font-black text-(--text-color) [&>.badge]:w-12 [&>.badge]:sm:w-12 [&>.badge]:md:w-16 [&>.badge]:lg:w-24 ">
        <img className="badge" src={Badge} alt="badge" />
        <h1>{TextData.title}</h1>
        <img className="badge" src={Badge} alt="badge" />
      </header>

      <main className="block space-y-6 h-3/5 w-4/5 overflow-y-auto bg-(--panel-color)/50 rounded-2xl p-5">
        {/* Fix alignment between info board and child board*/}
        <Board
          bgColor="bg-(--darkSecondary-color)/60"
          margin="mb-14"
          rank={TextData.board.rank}
          name={TextData.board.name}
          score={TextData.board.score}
          date={TextData.board.date}
          time={TextData.board.time}
        />
        {boardMapper()}
      </main>

      <form
        onSubmit={handleSubmit}
        className="flex gap-2 justify-between items-center text-center bg-white border-2 border-(--primary-color) w-80 lg:w-lg md:w-md sm:w-sm p-2 md:p-4 lg:p-4 sm:p-4 rounded-2xl"
      >
        <img className="w-8" src={Magnifier} alt="magnifier" />
        {/* fix search*/}
        <input
          onChange={(e) => setSearch(cleanInput(e.target.value))}
          className="text-black font-bold w-full indent-2 outline-none"
          type="text"
          placeholder={TextData.searchBar.placeholder}
        />
        
      </form>
    </div>
  );
}

export default Scores;
