# Ratchet-tests

## Description

This is automated smoking test.
The build is setup to work with Firefox, Chrome and PhantomJS. Have a look at the `build.gradle` and the `src/test/resources/GebConfig.groovy` files.

## Usage

The following commands will launch the tests with the individual browsers:
    
    ./gradlew chromeTest
    ./gradlew firefoxTest
    ./gradlew phantomJsTest

To run with all, you can run:

    ./gradlew test

To run with different environment, you can run:

    ./gradlew chromeTest -Denv=ENV_VAR
    EVN_VAR can be: develop, release, stable, hotfix

To run smoke functional testing, you can run:

    ./gradlew -DSPEC_TEST.single=SmokingFunctionalJUnitSpecRunner SPEC_TEST
    SPEC_TEST can be: chromeTest, firefoxTest, phantomJsTest

*Note: currently, we not support production.*

Replace `./gradlew` with `gradlew.bat` in the above examples if you're on Windows.