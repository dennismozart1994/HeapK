#!/bin/bash
# Grab the coverage value for the current branch from Sonarqube and print it in the CI console log
JSON=$(curl -u "$SONAR_TOKEN": https://sonarqube.ci.tcc.services/api/measures/component\?component\=Heap\&metricKeys\=coverage\&branch\=$CI_COMMIT_BRANCH)
COVERAGE=$(echo "$JSON" | sed -n 's/.*"value":"\([^"]*\)".*/\1/p')

echo Coverage: "$COVERAGE"