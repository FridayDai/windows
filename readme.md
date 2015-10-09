# Ratchet-tests

## Description

This is automated smoking test.
The build is setup to work with Firefox, Chrome and PhantomJS. Have a look at the `build.gradle` and the `src/test/resources/GebConfig.groovy` files.

## Usage

To run with different environment, you can run:

    ./gradlew chromeTest -Denv=ENV_VAR
    EVN_VAR can be: develop, release, stable, hotfix

To run all 3 portals functional testing, you can run:

    ./gradlew -DSPEC_TEST.single=AllSpecRunner SPEC_TEST
    SPEC_TEST is variable, can be: chromeTest, firefoxTest

### To use IE for testing, you should:

1.Run AdminSpecRunner with chrome or firefox, it should be

    ./gradlew -DSPEC_TEST.single=AdminSpecRunner SPEC_TEST
    SPEC_TEST is variable, can be: chromeTest, firefoxTest
    
2.Run ClientPatientSpecRunner with IE, it should be

    ./gradlew -DieTest.single=ClientPatientSpecRunner ieTest

*Note: currently, we not support production.*

Replace `./gradlew` with `gradlew.bat` in the above examples if you're on Windows.