#!/bin/zsh
sbt assembly

/usr/local/cellar/apache-spark/1.3.0/bin/spark-submit \
  --class "TestSpark" \
  target/scala-2.10/TestSpark-assembly-1.0.jar