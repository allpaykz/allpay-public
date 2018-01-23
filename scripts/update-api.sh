#!/bin/bash

# Get working directory. It is directory where this script is located
# http://stackoverflow.com/questions/59895/can-a-bash-script-tell-what-directory-its-stored-in
# Is a useful one-liner which will give you the full directory name
# of the script no matter where it is being called from
WORKING_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

SERVER_ADDRESS="${1:-http://127.0.0.1:8080/mfs}"
PROJECT_DIRECTORY="${2:-$WORKING_DIR}"

function run {
    # source: https://stackoverflow.com/questions/2657935/checking-for-a-dirty-index-or-untracked-files-with-git
    if ! $(git diff-index --quiet --cached HEAD --) ; then
       echo has staged changes
       exit 1
    fi
    if ! $(git diff-files --quiet) ; then
        echo has unstaged changes
        exit 2
    fi
    if u="$(git ls-files --others --exclude-standard)" && test -z "$u" ; then
        echo no new files
    else
        echo new files
        exit 3
    fi

    JAR_PATH="${1:-$HOME/scripts/swagger-gen}"
    JAR_NAME=swagger-codegen-cli.jar

    mkdir -p "$JAR_PATH"
    pushd "$JAR_PATH"
    if [ -f "$JAR_PATH/$JAR_NAME" ]; then
        echo "jar is found"
    else
        echo "jar was not found, downloading..."
        wget http://central.maven.org/maven2/io/swagger/swagger-codegen-cli/2.2.3/swagger-codegen-cli-2.2.3.jar -O "$JAR_NAME"
    fi


    java -jar swagger-codegen-cli.jar generate -l java -i "${SERVER_ADDRESS}"/webresources/swagger.json && echo generated

    cp -r src/main/java/* "$PROJECT_DIRECTORY"/src/main/java && echo files copied to "$PROJECT_DIRECTORY"/src/main/java

    popd

    git add -A && git commit -m "Upd: updated swagger at $(date)"
}

run