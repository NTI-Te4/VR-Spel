export interface BoardEntry {
  rank: string,
  username: string,
  score: string | number,
  date: string
}

function Board({
  bgColor,
  rank,
  name,
  score,
  date,
  time,
  margin,
}: {
  bgColor?: string;
  rank: string;
  name: string;
  score: string | number;
  date: string;
  time: string,
  margin?: string;
}) {
  return (
    <div
      className={`grid grid-cols-5 items-center text-center rounded-lg ${margin} ${
        bgColor ? bgColor : "bg-(--secondary-color)"
      } w-full h-[15%] font-bold text-(--text-color) text-[14px] sm:text-[20px] md:text-2xl lg:text-3xl`}
    >
      <label>{rank}</label>
      <label>{name}</label>
      <label>{score}</label>
      <label>{date}</label>
      <label>{time}</label>
    </div>
  );
}

export default Board;
