* [Linux - sed](#linux-sed)
* [Linux - jq](#linux-jq)

### <h2 id="linux-sed"> Linux sed Command - Stream Editor </h2>
sed is a text stream editor that used to edit files quickly and efficiently. the command searches through, replaces, adds, and delete lines in a text file without opening the file in a text editor.

The sed command processes each input File parameter by reading an input line into a <b>pattern space</b>, applying all sed subcommands in sequence whose addresses select that line, and writing the pattern space to standard output. It then clears the pattern space and repeats this process for each line specified in the input File parameter. 

#### sed syntax

sed OPTIONS... [SCRIPT][INPUTFILE...]

#### sed Subcommands- s/pattern/replacement/flags
ubstitutes the replacement string for the first occurrence of the pattern parameter in the pattern space. Any character that is displayed after the s subcommand can substitute for the / (slash) separator except for the space or new-line character.
See the Pattern Matching section of the ed command.

The value of the flags variable must be zero or more of:

g - Substitutes all non-overlapping instances of the pattern parameter rather than just the first one.
n  - Substitutes for the n-th occurrence only of the pattern parameter.
p - Writes the pattern space to standard output if a replacement was made.
w WFile - Writes the pattern space to the WFile variable if a replacement was made. Appends the pattern space to the WFile variable. If the WFile variable was not already created by a previous write by this sed script, the sed command creates it.

#### Example - find all files under current path and replace with a pattern

`find -type f -exec sed -i "s/pattern/whatyouwant/g" {} +`

NOTE: we use -exec [command] {} + to execute and terminate a command

#### Reference
[https://c.biancheng.net/view/4028.html](https://c.biancheng.net/view/4028.html)
[https://www.ibm.com/docs/hu/aix/7.2?topic=s-sed-command](https://www.ibm.com/docs/hu/aix/7.2?topic=s-sed-command)

### <h2 id="linux-jq">jq Command for JSON Processing </h2>

1. Access Properties by using . selector
curl http://api.open-notify.org/iss-now.json | jq '.message'

jq build a json:
```
 jq --null-input --arg 'releaseDate' "$(date --iso-8601=seconds)" --arg 'buildId' "123" '{pipelineTag:{"ReleaseDate":$releaseDate, "ReleaseVersion":$buildId}}'>variables.json
```

jq read:

```
cat prod.json | jq '.variables | paths(scalars) as $path | {"key":$path |join("."), "value": getpath($path)} | .value'
```


#### Reference
[https://www.baeldung.com/linux/jq-command-json](https://www.baeldung.com/linux/jq-command-json)
[https://jqlang.github.io/jq/manual/](https://jqlang.github.io/jq/manual/)