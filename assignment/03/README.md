# Assignment 3

## Parsing

### Exercise 1

#### 1.

Switching the order of `<word>` and `<wordList>` removes left-recursion, but exchanges it for right-recursion.
We introduce a new non-terminal `<optionalWordList>` and apply the generic transformation for a left-recursive derivation:

```
<sentence> ::= <wordList>.
<wordList> ::= <word><optionalWordList>
<optionalWordList> ::= <word><optionalWordList>
	| ε
```

This grammar is equivalent to the following, right-recursive grammar:

```
<sentence> ::= <wordList>.
<wordList> ::= <word><wordList>
	| <word>
```

#### 2.

Left-recursive grammar can not be handled by a top-down parser, because - depending on the derivation the parser tries to apply - it can end up with an infinitely long parse.
E.g. the a top-down parser using the left-recursive grammar above may always choose to apply the rule `<wordList> → <wordList><word>` when it tries to parse a `<wordList>`.
Since it won't consume any terminals in this case, it will never terminate.

### Exercise 2

```
<sentence> ::= <capitalWordList><finalizingPunctuation>
<capitalWordList> ::= <capitalWord><wordList>
	| <capitalWord>
<wordList> ::= <nonFinalizingPunctuation><wordList>
	| <word><wordList>
	| <word>
<word> ::= <capitalWord>
	| <nonCapitalWord>
<capitalWord> ::= <capitalLetter><wordRest>
<nonCapitalWord> ::= <nonCapitalLetter><wordRest>
<wordRest> ::= <nonCapitalLetter><wordRest>
	| ε
<capitalLetter> ::= [A-Z]
<nonCapitalLetter> ::= [a-z]
<finalizingPunctuation> ::= .
	| ?
	| !
<nonFinalizingPunctuation> ::= ,
	| :
	| ;
	| -
```

#### Regex

`<word> ::= [A-Za-z]+`, `<capitalWord> ::=[A-Z][A-Za-z]*`

### Exercise 3

```
<phoneNumber> ::= +<countryCode>(0)<areaCode><number>
<countryCode> ::= <digit>{1, 4}
<areaCode> ::= <digit>{2}
<number> ::= <digit>{6, 7}
```

`{a, b}` stands for `a` to `b` repetitions, `{a}` is short for `{a, a}`.

#### Regex

`\+\d{1,4}\(0\)\d{2}\d{6,7}`
