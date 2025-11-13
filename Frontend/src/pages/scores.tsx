import Badge from "../assets/badge.webp";
import Magnifier from "../assets/magnifier.webp";

function Scores() {
  return (
    <>
      <div className="leaderboard">
        <img src={Badge} alt="badge" />
        <h1>Leaderboard</h1>
        <img src={Badge} alt="badge" />
      </div>

      <main className="leaderboard-info">
        <div>
          <span>{}</span>
          <span>
            <label>{}</label>
            <label>{}</label>
            <label>{}</label>
          </span>
        </div>
        <div>
          {/* MAKE THIS A COMPONENT */}
          <span>{}</span>
          <span>
            <label>{}</label>
            <label>{}</label>
            <label>{}</label>
          </span>
        </div>
      </main>

      <form>
        <img src={Magnifier} alt="magnifier" />
        <input type="text" placeholder={""} />
        <button>{}</button>
      </form>

      <footer>
        <article>
          {" "}
          {/* MAKE INTO A COMPONENT */}
          <h2>{}</h2>
          <p>{}</p>
        </article>

        <article>
          <h2>{}</h2>
          <p>{}</p>
        </article>

        <article>
          <h2>{}</h2>
          <p>{}</p>
        </article>
      </footer>
    </>
  );
}

export default Scores;
