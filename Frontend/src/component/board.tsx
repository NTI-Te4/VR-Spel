export interface BoardEntry {
  rank: string,
  username: string,
  score: string | number,
  uploaded: string
}

function Board({
  bgColor,
  rank,
  name,
  score,
  date,
  margin,
}: {
  bgColor?: string;
  rank: string;
  name: string;
  score: string | number;
  date: string;
  margin?: string;
}) {
  return (
    <div
      className={`flex text-center items-center justify-around rounded-lg ${margin} ${
        bgColor ? bgColor : "bg-(--secondary-color)"
      } w-full h-[15%] font-bold text-(--text-color) text-[18px] md:text-2xl lg:text-3xl`}
    >
      <label>{rank}</label>
      <label>{name}</label>
      <label>{score}</label>
      <label>{date}</label>
    </div>
  );
}

export default Board;
