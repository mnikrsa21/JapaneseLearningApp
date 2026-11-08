#!/usr/bin/env bash
ORG_GRADLE_PROJECT_arg0=${ORG_GRADLE_PROJECT_arg0:-"japaneselearning"}
ORG_GRADLE_PROJECT_android applicationId="com.japaneselearning"
./gradlew clean assembleDebug assembleRelease