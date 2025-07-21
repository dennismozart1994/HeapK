#!/bin/bash
# This script takes care of reporting to SonarQube code coverage info for the repository.
export BUILD_OUTPUT_DIR="$GITHUB_WORKSPACE/shared/build"

echo "sonarqube_scan.sh WORKSPACE $GITHUB_WORKSPACE"
echo "sonarqube_scan.sh BUILD_OUTPUT_DIR $BUILD_OUTPUT_DIR"
echo "sonarqube_scan.sh REPOSITORY $GITHUB_REPOSITORY"
echo "sonarqube_scan.sh COMMIT_SHA $GITHUB_SHA"
echo "sonarqube_scan.sh REF $GITHUB_REF"

BRANCH_NAME=""
if [[ "$GITHUB_REF" != "refs/heads/main" ]]; then
    BRANCH_NAME=$GITHUB_REF
fi

# if pull request sonarqube requires different params to avoid sonar.branch.name exception in MRs
  if [[ -n "$PR_NUMBER" ]]; then
  export SONAR_PARAMS="-Dsonar.pullrequest.key=$PR_NUMBER -Dsonar.pullrequest.branch=$GITHUB_REF -Dsonar.pullrequest.base=$BASE_BRANCH"
else
  export SONAR_PARAMS="-Dsonar.branch.name=$BRANCH_NAME"
fi

./gradlew sonar \
    -Dsonar.token="$SONAR_TOKEN" \
    $SONAR_PARAMS \
    -Dsonar.projectKey="$SONAR_PROJECT_KEY" \
    -Dsonar.organization="$SONAR_ORG" \
    -Dsonar.projectName="HeapK" \
    -Dsonar.projectVersion="$GITHUB_SHA" \
    -Dsonar.sourceEncoding="UTF-8" \
    -Dsonar.qualitygate.wait="true" \
    -Dsonar.coverage.jacoco.xmlReportPaths="$BUILD_OUTPUT_DIR/reports/kover/report.xml" \
    -Dsonar.android.lint.report="$BUILD_OUTPUT_DIR/reports/lint-results*.xml" \
    -Dsonar.junit.reportsPath="$BUILD_OUTPUT_DIR/test-results/testDebugUnitTest,$BUILD_OUTPUT_DIR/test-results/iosSimulatorArm64Test" \
    -Dsonar.sources="src/androidMain/kotlin,src/commonMain/kotlin,src/iosMain/kotlin" \
    -Dsonar.exclusions="**/androidApp/src/main/**,**/*.ios.kt,**/*.xml,**/*.json,**/*.db,**/res/**/*,**/commonTest/**/*,**/iosTest/**/*" \
    -Dsonar.coverage.exclusions="**/androidApp/src/main/**,**/*.ios.kt,**/*.xml,**/*.json,**/*.db,**/res/**/*,**/commonTest/**/*,**/iosTest/**/*" \
    -Dsonar.cpd.exclusions="**/androidApp/src/main/**,**/*.ios.kt,**/*.xml,**/*.json,**/*.db,**/res/**/*,**/commonTest/**/*,**/iosTest/**/*" \
    -Dsonar.tests="src/androidUnitTest/kotlin,src/commonTest/kotlin,src/iosTest/kotlin" \
    -Dsonar.verbose=true
