# Contributing guidelines

## Contributing a fix checklist

+ [ ] Pull request must have a reference to the issue it fixes. Just add a snippet "Fix for issue #number" to a pull request description.
+ [ ] Each contributed commit must have the issue number in its comments. Like "[#number] fixed this or that"
+ [ ] Each new or modified class must follow [Atom specification](https://github.com/project-avral/oo-atom/blob/master/ATOM_SPECIFICATION.md), unless it is prooved not possible.
+ [ ] Each new class or class modifications must be covered with tests on 100%

## Puzzle-driven development

The project uses `0pdd` tool for organizing puzzle-driven development. If for some reason you cannot strictly follow the checklist above,
or you are contributing a partial fix or improvement,  putting `todo` puzzles on stubbed places will significantly increase chances to pass
pull request's review.

See [here](http://www.yegor256.com/2010/03/04/pdd.html) for the information about the process.

## Test coverage

Pitest is used for test coverage. Test coverage is enabled and disabled together with the tests (Maven option `-DskipTests`).
Also, you can estimate a coverage of a single test module by executing `$ mvn clean install -Dpitest.target.tests=[fully qualified test class name]`.
Use this option to estimate how focused is your test module (I assume that the module is focused if the coverage of code which doesn't belong
to the module under the test is close to 0%).

