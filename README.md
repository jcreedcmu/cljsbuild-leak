Unexpected behavior with lein-figwheel
--------------------------------------

What I did was:

```shell
lein clean && lein figwheel foo
cat build/foo/js/out/pack/core.js
```

and I saw

```javascript
// Compiled by ClojureScript 0.0-3269 {}
goog.provide('pack.core');
goog.require('cljs.core');
cljs.core.print.call(null,new cljs.core.Keyword(null,"bar","bar",-1386246584));
```

and I was surprised to see the keyword `:bar` being constructed rather
than `:foo`. In `src/both/pack/core.cljs` the macro `env` is invoked,
and I expect (when building build-id `foo`) to use the definition of
that macro in `src/foo/pack/env.clj`, not the one in
`src/bar/pack/env.clj`. Is this a bug?

If I delete the entire build
```clojure
                       :bar {:source-paths ["src/both" "src/bar"]
                             :compiler {:main          "pack.core"
                                        :output-dir    "build/bar/js/out"
                                        :output-to     "build/bar/app.js"}}
```

from `project.clj` and do

```shell
lein clean && lein figwheel foo
cat build/foo/js/out/pack/core.js
```

again, I see

```javascript
// Compiled by ClojureScript 0.0-3269 {}
goog.provide('pack.core');
goog.require('cljs.core');
cljs.core.print.call(null,new cljs.core.Keyword(null,"foo","foo",1268894036));
```

as I expect.

Incidentally, I also get the expected behavior
(`cljs.core.Keyword(null,"foo","foo",...)` emitted) when directly
running ```lein cljsbuild once foo```.
