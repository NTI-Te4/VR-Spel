function Credit({ title, firstDev, secDev }
    : { 
        title: string; 
        firstDev: string;
        secDev: string; 
    }) {
  return (
    <section>
      <h2>{title}</h2>
      <p>{firstDev} {secDev}</p>
    </section>
  );
}

export default Credit;
