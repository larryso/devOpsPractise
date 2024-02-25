# google/jsonnet - the data templating language

Jsonnet is a data templating language that extends json syntax, adding constructs for generating, translating and refinning data.

It is intended for projects with complex configurations that benefit from advanced scripting capabilities and code re-use.

## Syntax
Let's start with an example that does not involve any computation but uses new syntax.

```jsonnet
/* A C-style comment. */
# A Python-style comment.
{
  cocktails: {
    // Ingredient quantities are in fl oz.
    'Tom Collins': {
      ingredients: [
        { kind: "Farmer's Gin", qty: 1.5 },
        { kind: 'Lemon', qty: 1 },
        { kind: 'Simple Syrup', qty: 0.5 },
        { kind: 'Soda', qty: 2 },
        { kind: 'Angostura', qty: 'dash' },
      ],
      garnish: 'Maraschino Cherry',
      served: 'Tall',
      description: |||
        The Tom Collins is essentially gin and
        lemonade.  The bitters add complexity.
      |||,
    },
    Manhattan: {
      ingredients: [
        { kind: 'Rye', qty: 2.5 },
        { kind: 'Sweet Red Vermouth', qty: 1 },
        { kind: 'Angostura', qty: 'dash' },
      ],
      garnish: 'Maraschino Cherry',
      served: 'Straight Up',
      description: @'A clear \ red drink.',
    },
  },
}
```
* 其中的对象，可以不用引号
* 数组里最后一个对象的后面, 要多加一个 逗号
* 单引号, 双引号, 作用一样都表示字符串, 但是 双引号中的 单引号 可以不用转义
* |||块 可以表达多行字符串
* @开头的字符串是逐字字符串，字符串中保留所有的空格、换行和特殊字符，而不会进行转义，只能是单行字符串

## Variables
1. The local keyword defines a variable.
2. Variables defined next to fields end with a comma (,).
3. All other cases end with a semicolon (;).

```jsonnet
// A regular definition.
local house_rum = 'Banks Rum';

{
  // A definition next to fields.
  local pour = 1.5,

  Daiquiri: {
    ingredients: [
      { kind: house_rum, qty: pour },
      { kind: 'Lime', qty: 1 },
      { kind: 'Simple Syrup', qty: 0.5 },
    ],
    served: 'Straight Up',
  },
  Mojito: {
    ingredients: [
      {
        kind: 'Mint',
        action: 'muddle',
        qty: 6,
        unit: 'leaves',
      },
      { kind: house_rum, qty: pour },
      { kind: 'Lime', qty: 0.5 },
      { kind: 'Simple Syrup', qty: 0.5 },
      { kind: 'Soda', qty: 3 },
    ],
    garnish: 'Lime wedge',
    served: 'Over crushed ice',
  },
}
```
## References
Another way to avoid duplication is to refer to another part of the structure.
* self refers to the current object.
* $ refers to the outer-most object.
['foo'] looks up a field.
.foo can be used if the field name is an identifier.
[10] looks up an array element.
Arbitrarily long paths are allowed.
Array slices like arr[10:20:2] are allowed, like in Python.
Strings can be looked up / sliced too, by unicode codepoint.
```
{
  'Tom Collins': {
    ingredients: [
      { kind: "Farmer's Gin", qty: 1.5 },
      { kind: 'Lemon', qty: 1 },
      { kind: 'Simple Syrup', qty: 0.5 },
      { kind: 'Soda', qty: 2 },
      { kind: 'Angostura', qty: 'dash' },
    ],
    garnish: 'Maraschino Cherry',
    served: 'Tall',
  },
  Martini: {
    ingredients: [
      {
        // Use the same gin as the Tom Collins.
        kind:
          $['Tom Collins'].ingredients[0].kind,
        qty: 2,
      },
      { kind: 'Dry White Vermouth', qty: 1 },
    ],
    garnish: 'Olive',
    served: 'Straight Up',
  },
  // Create an alias.
  'Gin Martini': self.Martini,
}
```

## Abstraction
jsonnet has rich abstraction features:
* Imports
* Merging
* Functions

#### Imports
Just as other languages, Jsonnet allows code to be imported from other files:
`local secret = import "./secret.libsonnet`
The exported object (the only non-local one) of secret.libsonnet is now available as a local variable called secret.

#### Merging
Deep merging allows you to change parts of an object without touching all of it. Consider the following example:
```json
local secret = {
  kind: Secret,
  metadata: {
    name: "mySecret",
    namespace: "default", // need to change that
  },
  data: {
    foo: std.base64("foo")
  }
};
```
To change the namespace only, we can use the special merge key +: like so:
```json
// define the patch:
local patch = {
  metadata+: {
    namespace: "myApp"
  }
}
```
The difference between : and +: is that the former replaces the original data at that key, while the latter applies the new object as a patch on top, meaning that values will be updated if possible but all other stay like they are.
To merge those two, just add (+) the patch to the original:
`secret + patch`
The output of this is the following JSON object:
```json
{
  "kind": "Secret",
  "metadata": {
    "name": "mySecret",
    "namespace": "myApp"
  },
  "data": {
    "foo": "Zm9vCg=="
  }
}
```

#### [Functions](https://jsonnet.org/learning/tutorial.html#functions) 
Like Python, functions have positional parameters, named parameters, and default arguments. Closures are also supported. The examples below should demonstrate the syntax. Many functions are already defined in the [standard library](https://jsonnet.org/ref/stdlib.html).

```json
// Define a local function.
// Default arguments are like Python:
local my_function(x, y=10) = x + y;

// Define a local multiline function.
local multiline_function(x) =
  // One can nest locals.
  local temp = x * 2;
  // Every local ends with a semi-colon.
  [temp, temp + 1];

local object = {
  // A method
  my_method(x): x * x,
};

{
  // Functions are first class citizens.
  call_inline_function:
    (function(x) x * x)(5),

  call_multiline_function: multiline_function(4),

  // Using the variable fetches the function,
  // the parens call the function.
  call: my_function(2),

  // Like python, parameters can be named at
  // call time.
  named_params: my_function(x=2),
  // This allows changing their order
  named_params2: my_function(y=3, x=2),

  // object.my_method returns the function,
  // which is then called like any other.
  call_method1: object.my_method(3),

  standard_lib:
    std.join(' ', std.split('foo/bar', '/')),
  len: [
    std.length('hello'),
    std.length([1, 2, 3]),
  ],
}
```
output will be"
```json
{
  "call": 12,
  "call_inline_function": 25,
  "call_method1": 9,
  "call_multiline_function": [8, 9],
  "len": [
    5,
    3
  ],
  "named_params": 12,
  "named_params2": 5,
  "standard_lib": "foo bar"
}
```
## Reference

[https://jsonnet.org/learning/tutorial.html](https://jsonnet.org/learning/tutorial.html)

[https://zhuanlan.zhihu.com/p/640935478](https://zhuanlan.zhihu.com/p/640935478)