cd $(dirname $0)
cd ../project
mvn clean package
java -jar target/wiitty-manager-1.0.0.jar &
PID=$!
sleep 15
curl -s http://localhost:8089/wiitty/api/scenarios > target/actual_scenarios.json
curl -s http://localhost:8089/wiitty/api/scenarios?lang=en > target/actual_scenarios_EN.json
curl -s http://localhost:8089/wiitty/api/scenarios?lang=fr > target/actual_scenarios_FR.json
kill -9 $PID

echo "Let's look at the actual results: `cat target/actual_scenarios.json`"
echo "And compare it to: `cat ../test/expected_scenarios_EN.json`"
if diff -w ../test/expected_scenarios_EN.json target/actual_scenarios.json
    then
        echo SUCCESS
        let ret=0
    else
        echo FAIL
        let ret=255
        exit $ret
fi

echo "Let's look at the actual results: `cat target/actual_scenarios_EN.json`"
echo "And compare it to: `cat ../test/expected_scenarios_EN.json`"
if diff -w ../test/expected_scenarios_EN.json target/actual_scenarios_EN.json
    then
        echo SUCCESS
        let ret=0
    else
        echo FAIL
        let ret=255
        exit $ret
fi

echo "Let's look at the actual results: `cat target/actual_scenarios_FR.json`"
echo "And compare it to: `cat ../test/expected_scenarios_FR.json`"
if diff -w ../test/expected_scenarios_FR.json target/actual_scenarios_FR.json
    then
        echo SUCCESS
        let ret=0
    else
        echo FAIL
        let ret=255
        exit $ret
fi

rm -rf target

exit
