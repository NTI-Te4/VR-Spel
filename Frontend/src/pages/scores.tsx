import Badge from "../assets/img/badge.webp";
import Magnifier from "../assets/img/magnifier.webp";
import Board from "../component/board";
import TextData from "../assets/json/scorePage.json";
import { useState, useEffect, type FormEvent } from "react";
import { type BoardEntry } from "../component/board";
import axios from "axios";

function Scores() {
  const [search, setSearch] = useState<string>("");
  const [boards, setBoards] = useState<BoardEntry[]>([]);

  useEffect(() => {
    axios
      .get("/api/score/getScore")
      .then(res => setBoards(res.data))
      .catch((err) => console.error(err));
  }, []);

  const boardMapper = () => boards.map((entry, index) => (
    // LIMITS: cut username by length 10
    <Board 
      bgColor="bg-(--secondary-color)"
      key={index}
      rank={`#${index}`}
      name={entry.username}
      score={entry.score}
      date={entry.uploaded}
    />
  )) 

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
  };

  return (
    <div className=" flex flex-col justify-around items-center h-screen overflow-auto">
      <header className="flex gap-4 text-4xl sm:text-5xl md:text-6xl lg:text-7xl xl:text-8xl font-black text-(--text-color) [&>.badge]:w-12 [&>.badge]:sm:w-12 [&>.badge]:md:w-16 [&>.badge]:lg:w-24 ">
        <img className="badge" src={Badge} alt="badge" />
        <h1>{TextData.title}</h1>
        <img className="badge" src={Badge} alt="badge" />
      </header>

      <main className="block space-y-6 h-3/5 lg:w-3/5 w-4/5 overflow-y-auto bg-(--panel-color)/50 rounded-2xl p-5">
        {/* Fix alignment between info board and child board*/}
        <Board
          bgColor="bg-(--darkSecondary-color)/75"
          margin="mb-20"
          rank={TextData.board.rank}
          name={TextData.board.name}
          score={TextData.board.score}
          date={TextData.board.date}
        />
        {!boards 
          ? <div className="m-auto">Loading data...</div> 
          : boardMapper()
        }
      </main>

      <form
        onSubmit={handleSubmit}
        className="flex gap-2 justify-between items-center text-center bg-white border-2 border-(--primary-color) w-80 lg:w-lg md:w-md sm:w-sm p-2 md:p-4 lg:p-4 sm:p-4 rounded-2xl"
      >
        
        <img className="w-8" src={Magnifier} alt="magnifier" />
        {/* Sanitize Input */}
        <input
          className="text-black font-bold w-full indent-2 outline-none"
          type="text"
          placeholder={TextData.searchBar.placeholder}
        />
        <button className="bg-(--primary-color) px-5 py-1 rounded text-(--text-color) font-black hover:opacity-85 border-b-6 border-2 border-(--secondary-color) active:border-2 transition-opacity duration-300">
          {TextData.searchBar.searchBtn}
        </button>
      </form>
    </div>
  );
}

export default Scores;
