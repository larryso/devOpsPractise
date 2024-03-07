@echo off
set current=%cd%
pushd ..
set parent=%cd%
popd

docker run -v %parent%:/src --rm larryso/jsonnet:v0.1 /src/config/demo.jsonnet