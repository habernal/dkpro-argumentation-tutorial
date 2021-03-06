# DKPro Argumentation Tutorial - Example project for reading annotated data

as shown in

> Habernal, I., Eckle-Kohler, J., & Gurevych, I. (2014). Argumentation Mining on the Web from Information Seeking Perspective. In E. Cabrio, S. Villata, & A. Wyner (Eds.), Proceedings of the Workshop on Frontiers and Connections between Argumentation Theory and Natural Language Processing (pp. 26-39). Bertinoro, Italy: CEUR-WS. Retrieved from http://ceur-ws.org/Vol-1341/

+ver 0.0.2/2015-08-24

## Contact

Ivan Habernal

* https://www.ukp.tu-darmstadt.de/people/postdoctoral-researchers/dr-ivan-habernal

## Requirements

- Java JDK 1.7 and higher
- Maven3
- Annotated data
  - packaged separately, available at https://www.ukp.tu-darmstadt.de/data/argumentation-mining/argument-annotated-user-generated-web-discourse/

## How-to

1. Modify paths to gold data
  - Modify `de.tudarmstadt.ukp.dkpro.argumentation.tutorial.ArgumentationCorpusDebugger` and set the `annotatedCorpusDir` variable to point to the gold data located in `gold.data.toulmin` directory
  - Similarly `de.tudarmstadt.ukp.dkpro.argumentation.tutorial.PersuasiveDocumentsCorpusDebugger` to `gold.data.persuasive` directory
2. Run `ArgumentationCorpusDebugger` (or `PersuasiveDocumentsCorpusDebugger`)
  - It will print annotated argument components, relations, and other info to the std. out
3. Explore it further!
  - Have a look at `de.tudarmstadt.ukp.dkpro.argumentation.io.writer.ArgumentDumpWriter` from the `de.tudarmstadt.ukp.dkpro.argumentation.0.0.2-SNAPSHOT-minimal` package which shows how to access the argument components, their text, tokens, sentences, etc.
