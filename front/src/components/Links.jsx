function Links() {
  return (
    <section id="links-bg">
      <article aria-labelledby="links" className="container" id="links">
        <div>
          <img
            src={`${import.meta.env.BASE_URL}/img/Linkedin.svg`}
            alt="Linkedin"
          />
          <a href="https://www.linkedin.com/in/gabriel-do-rego-farias-138378322/">
            Gabriel
          </a>{" "}
          <p>&</p>
          <a href="https://www.linkedin.com/in/hugo-resende/">Hugo</a> <p>&</p>
          <a href="https://www.linkedin.com/in/emily-gomes-miranda/">Emily</a>
        </div>
        <div>
          <img
            src={`${import.meta.env.BASE_URL}/img/Github.svg`}
            alt="Github"
          />
          <a href="https://github.com/Gabriel-Farias-06">Gabriel</a> <p>&</p>
          <a href="https://github.com/Hugo-Oliveira-RD11">Hugo</a> <p>&</p>
          <a href="https://github.com/emi-ly111">Emily</a>
        </div>
        <div>
          <img
            src={`${import.meta.env.BASE_URL}/img/Instagram.svg`}
            alt="Instagram"
          />
          <a href="https://www.instagram.com/gabriel___farias___/">Gabriel</a>{" "}
          <p>&</p>
          <a href="#">Hugo</a> <p>&</p>
          <a href="#">Emily</a>
        </div>
      </article>
    </section>
  );
}

export default Links;
