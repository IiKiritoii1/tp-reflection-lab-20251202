# TP Reflection Lab

This repository contains the Java TP that demonstrates dynamic class loading, dependency injection via reflection, and invoking methods at runtime.

Structure
- `pom.xml`  Maven project file
- `config.txt`  contains fully-qualified class names for DAO and Metier (one per line)
- `src/main/java`  main source code
  - `dao/IDao.java`
  - `dao/DaoImpl.java`
  - `metier/IMetier.java`
  - `metier/MetierImpl.java`
  - `presentation/Presentation2.java`  main class that reads `config.txt`, instantiates classes by reflection, injects DAO into Metier and prints the result
- `src/test/java`  unit tests (JUnit)

How to run (Maven)

```powershell
# run tests
mvn test

# run application (reads config.txt at project root)
mvn compile exec:java
```

How to run without Maven

```powershell
mkdir out
javac -d out src\main\java\dao\*.java src\main\java\metier\*.java src\main\java\presentation\*.java
java -cp out presentation.Presentation2
```

Expected output

```
Résultats = 200.0
```

Notes
- `config.txt` should contain:
```
dao.DaoImpl
metier.MetierImpl
```
- CI: this repository includes a GitHub Actions workflow that runs `mvn test` on push and pull requests.

---

## Full README (extended)

Repository: https://github.com/IiKiritoii1/tp-reflection-lab-20251202

![Java CI](https://github.com/IiKiritoii1/tp-reflection-lab-20251202/actions/workflows/maven.yml/badge.svg)

Description
-----------
This project implements the lab from the screenshots: it shows how to load classes dynamically in Java, inject dependencies using reflection (setter injection), and invoke methods on those dynamically-created instances.

Files and purpose
- `config.txt`  located at project root; contains the fully-qualified class names for the DAO and Metier implementations, each on its own line (this is what `Presentation2` reads at runtime).
- `src/main/java/dao/IDao.java`  DAO interface with `double getValue()`
- `src/main/java/dao/DaoImpl.java`  concrete DAO returning `100.0`
- `src/main/java/metier/IMetier.java`  Metier interface with `double calcul()`
- `src/main/java/metier/MetierImpl.java`  concrete Metier with `setDao(IDao)` and `calcul()` returning `dao.getValue() * 2`
- `src/main/java/presentation/Presentation2.java`  main entry point that reads `config.txt`, instantiates classes using `Class.forName(...)`, injects DAO into Metier via the `setDao` method found by reflection, and prints the result.
- `pom.xml`  Maven build; includes JUnit dependency and an `exec` configuration to run the main class.
- `src/test/java/metier/MetierImplTest.java`  JUnit test asserting `MetierImpl.calcul()` returns `200.0` with `DaoImpl`.
- `.github/workflows/maven.yml`  GitHub Actions workflow that runs `mvn test` on pushes and pull requests.

How to run locally (recommended: Maven)

1. Ensure Java 17+ and Maven are installed.
2. From the project root (where `config.txt` is):

```powershell
mvn test
mvn compile exec:java
```

How to run without Maven (javac/java)

```powershell
mkdir out
javac -d out src\main\java\dao\*.java src\main\java\metier\*.java src\main\java\presentation\*.java
java -cp out presentation.Presentation2
```

Program output
--------------
When run against the included implementations (`dao.DaoImpl` and `metier.MetierImpl`) the program prints:

```
Résultats = 200.0
```

Note about encoding: on some Windows PowerShell configurations accented characters can display incorrectly; numerics and logic are unaffected. If you see accent rendering issues, enable UTF-8 in the terminal before running:

```powershell
chcp 65001
mvn compile exec:java
```

Publishing and CI
-----------------
- Repository URL: https://github.com/IiKiritoii1/tp-reflection-lab-20251202
- CI is enabled via GitHub Actions and runs `mvn test` on each push/PR.

If you want any of the following, tell me which and I'll add it:
- Add a simple HTTP endpoint that returns the result (small server in `Presentation2`).
- Add a release and a version tag.
- Add more unit tests or integration tests.

License
-------
This repository contains example code for learning purposes. Add a license file if you plan to publish or reuse.
