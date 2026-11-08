#!/usr/bin/env sh
#
# Copyright © 2015-2021 the original authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# This is a stub for the Gradle wrapper script.
# GitHub Actions runners have Gradle pre-installed.

if [ -x "$(command -v gradle)" ]; then
    GRADLE_CMD="gradle"
elif [ -x "$(command -v gradlew)" ]; then
    GRADLE_CMD="gradlew"
else
    echo "Error: gradle not found. Please install Gradle."
    exit 1
fi

exec "$GRADLE_CMD" "$@"