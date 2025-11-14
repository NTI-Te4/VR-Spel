import Credit from "../component/credit";
import TextData from "../assets/json/scorePage.json";

function AboutPage() {
  return (
    <div className="flex justify-center items-center w-screen h- bg-(--panel-color) ">
      <Credit
        title={TextData.credits.frontend.title}
        firstDev={TextData.credits.frontend.jack}
        secDev={TextData.credits.frontend.jonas}
      />
    </div>
  );
}

export default AboutPage;
