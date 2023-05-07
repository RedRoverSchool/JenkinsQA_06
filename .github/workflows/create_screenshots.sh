#!/bin/bash
if [ -f "target/surefire-reports/testng-results.xml" ]; then
    exit 0
fi
mkdir -p .screenshot
for f in $(find . -name "*Test.java"); do
    test_name=$(basename $f .java)
    if [ -f "target/surefire-reports/${test_name}.xml" ]; then
        continue
    fi
    mvn -Dtest=${test_name} test
    ./take_screenshot.sh ${test_name}
done