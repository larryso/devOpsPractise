# # google/jsonnet - the data templating language

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


## Reference

[https://jsonnet.org/learning/tutorial.html](https://jsonnet.org/learning/tutorial.html)

[https://zhuanlan.zhihu.com/p/640935478](https://zhuanlan.zhihu.com/p/640935478)