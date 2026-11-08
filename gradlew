#!/usr/bin/env bash
# Gradle wrapper script
if command -v gradle &> /dev/null; then
    gradle "$@"
else
    echo "Please install gradle or use GitHub Actions with pre-installed gradle"
    exit 1
fi