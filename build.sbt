name := "simplestest"

version := "1.0"

scalaVersion := "2.13.12"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  // Injeção de dependência com Guice
  guice,

  // JPA e JDBC
  javaJpa,
  jdbc,
  "org.hibernate.orm" % "hibernate-core" % "6.1.7.Final",
  "javax.persistence" % "javax.persistence-api" % "2.2",

  // Hibernate Validator compatível com javax.validation
  "org.hibernate.validator" % "hibernate-validator" % "6.1.7.Final",
  "javax.validation" % "validation-api" % "2.0.1.Final",
  "org.glassfish" % "javax.el" % "3.0.1-b12",

  // Jackson para Date/Time
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.14.3",

  // Driver PostgreSQL
  "org.postgresql" % "postgresql" % "42.6.0",

  // Lombok
  "org.projectlombok" % "lombok" % "1.18.30" % Provided,

  "io.swagger.core.v3" % "swagger-annotations" % "2.2.15",
  "io.swagger.core.v3" % "swagger-models" % "2.2.15",

// Testes
  "org.junit.jupiter" % "junit-jupiter-api" % "5.10.1" % Test,
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.10.1" % Test,
  "org.mockito" % "mockito-core" % "4.11.0" % Test,
  "org.mockito" % "mockito-junit-jupiter" % "4.11.0" % Test
)

// Corrige conflitos com scala-xml
ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % "always"

// Relaxa conflitos de dependência
evictionErrorLevel := Level.Warn

// Permite execução e testes isolados
run / fork := true
Test / fork := true

// Testes JUnit com verbosity
Test / testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")

// Overrides para manter dependências específicas
dependencyOverrides ++= Seq(
  "javax.persistence" % "javax.persistence-api" % "2.2",
  "javax.validation" % "validation-api" % "2.0.1.Final",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.14.3",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.14.3",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.3",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.3",
  "org.hibernate.validator" % "hibernate-validator" % "6.1.7.Final"
)

// Diretório de recursos explícito
Compile / resourceDirectories := Seq(
  baseDirectory.value / "src" / "main" / "resources"
)

// Copia manual do persistence.xml para o destino compilado
Compile / compile := {
  val analysis = (Compile / compile).value

  val source = baseDirectory.value / "src" / "main" / "resources" / "META-INF" / "persistence.xml"
  val target = baseDirectory.value / "target" / "scala-2.13" / "classes" / "META-INF" / "persistence.xml"

  if (source.exists) IO.copyFile(source, target)

  analysis
}
