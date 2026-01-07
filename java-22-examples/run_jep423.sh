#!/usr/bin/env bash
set -euo pipefail

# Run from this script's directory so relative paths (build/, ./gradlew) resolve correctly.
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT_DIR"

./gradlew --no-daemon clean runJep423

ls -lh build/gc-jep423.log && tail -n 20 build/gc-jep423.log | cat

