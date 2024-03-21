package edu.itmo.ilang.semantic

import edu.itmo.ilang.ir.Program
import edu.itmo.ilang.semantic.analysis.FunctionReturnAnalyzer
import edu.itmo.ilang.semantic.checkers.AssignNewValueToArgument
import edu.itmo.ilang.semantic.checkers.BreakAndContinueInsideCyclesChecker
import edu.itmo.ilang.semantic.checkers.ForRangeIsInteger
import edu.itmo.ilang.semantic.checkers.FunctionReturnChecker
import edu.itmo.ilang.semantic.transformations.DeadCodeEliminator

class SemanticStageProcessor {
    private val analysers = listOf(
        FunctionReturnAnalyzer()
    )

    private val checkers = listOf(
//        ReservedKeywordsChecker(), todo: enable when it will be implemented
//        TypeChecker(), todo: enable when it will be implemented
        FunctionReturnChecker(),
        BreakAndContinueInsideCyclesChecker(),
        AssignNewValueToArgument(),
        ForRangeIsInteger(),
    )

    private val transformers = listOf(
        DeadCodeEliminator(),
//        LazyBoolOperationsTransformer(), todo: enable when it will be implemented
    )

    fun process(program: Program) {
        for (analyser in analysers) {
            analyser.analyse(program)
        }

        for (checker in checkers) {
            checker.check(program)
        }

        for (transformer in transformers) {
            transformer.transform(program)
        }
    }
}
