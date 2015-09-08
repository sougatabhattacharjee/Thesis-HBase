grammar NotaQL;

/*
@header {
package hbase.transformation.notaql.antlr4;
}
*/


/**
    the complete notaql expression
    @date February 14, 2015
    @author sougata (modified from the previous grammar)
*/

notaql: transformation ';'? EOF;

/** a single transformation int he good old soon to be depricated three line style */
transformation: (inRowPredicate ',' (NL)*)?
                rowSpec ',' (NL)*
                cellSpec
                (',' (NL)* outRowPredicate)?;

/** a row specification */
rowSpec: OUT '.' ROW ARROW splittableVData;
/** a column specification */
//cellSpec: outputCol ARROW (vData | aggFun '(' vData? (',' atom)* ')');
cellSpec: outputCol ARROW (splittableVData | aggFun '(' splittableVData? (',' atom)* ')');
/** the aggregation function */
aggFun: fun=(AVG | COUNT | MAX | MIN | SUM | IMPLODE);

/** the input source from a column */
input
    : IN '.' ROW                                                                        #rowInput
    | IN '.' (colFamilyFilter=ColName ':')? source=(COL | VAL) ('?(' predicate ')')?    #cellInput
    | IN '.' colId                                                                      #colIdInput
    ;

/**
    An output column may feature a column id or a family prefixed input variable
*/
outputCol
    : OUT '.' colId                                                 #colIdColPath
    | OUT '.' (colFamily=ColName ':')? '$(' splittableVData ')'     #resolvedColPath
    ;

/**
    value data represents complex data which may include the following features:
        - input sources
        - current cell reference
        - column identifiers
        - atoms
        - column counter
        - braces
        - multiplication and division
        - addition and subtraction

    Note: the order of the operators guarantees that the operators are executed in the correct order
*/
vData
    : input                         #inputVData
    | '@'                           #currentCellVData
    | atom                          #atomVData
    | vDataFunction                 #functionVData
    | vDataGenericFunction          #genericFunctionVData
    | '(' vData ')'                 #bracedVData
    | vData op=(MULT|DIV) vData     #multiplicatedVData
    | vData op=(PLUS|MINUS) vData   #addedVData
    ;

/**
    a vdata function may be used within vdata and may provide:
        - the column count
*/
vDataFunction
    //: 'COL_COUNT(' vData? ')'        #colCountFunction
    : 'COL_COUNT(' (IN '.' colFamily=ColName)? ')'        #colCountFunction
    ;

vDataGenericFunction
        : (FunctionName = ColName)('(' vData? (',' atom)* ')')?       #genericFunction
        ;


/**
    represents splittable vdata - this means that the argument vData my be split into multiple entries
*/
splittableVData
    : vData                                             #vDataSplittableVData
    | baseVData=vData '.' splittingFunction=(SplitFunction | ListSplitFunction) '(' vData* ')'      #splitFunctionSplittableVData
    ;

SplitFunction: 'split';
ListSplitFunction: 'splitList';

/**
    A predicate may consist of the following checks:
        - column existence check
        - braces
        - negation
        - and/or with correct precedence
        - column to column/value comparisons (all obvious cases)

    Note: This does not support 'NOT' right now for simplicity reasons
*/
predicate
    : colId                                                     #colExistencePredicate
    | '(' predicate ')'                                         #bracedPredicate
    | '!' predicate                                             #negatedPredicate
    | predicate AND predicate                                   #andPredicate
    | predicate OR predicate                                    #orPredicate
    | vData op=(LT | LTEQ | GT | GTEQ) vData                    #relationalPredicate
    | vData op=(EQ | NEQ) vData                                 #equalityPredicate
    ;

/**
    An in row predicate is identified by a leading "IN:"
*/
inRowPredicate
    : IN ':' predicate
    ;

/**
    An out row predicate is identified by a leading "OUT:"
*/
outRowPredicate
    : OUT ':' predicate
    ;

/**
    A column identifier consists of an optional family and a column name
*/
colId
    : (colFamily=ColName ':')? colName=ColName (',' (colFamily=ColName ':')? colName=ColName)*   #stringColId
    | (colFamily=atom ':')? colName=atom                                                         #genericColId
    ;

/**
    Represents an atomic value (Int, Float or String) all wrapped in apostrophes
    Note: The apostropes surrounding the numbers are necessary due to potential clashes with the column names
*/
atom
    : (Int | Float)     #numberAtom
    | String            #stringAtom
    ;

/**
    Skip comments
*/
SL_COMMENT: '#' .*? '\n' -> skip;

ARROW: '<-';

IN: 'IN';
OUT: 'OUT';

ROW: '_r';
COL: '_c';
VAL: '_v';

EQ: '=';
NEQ: '!=';
LT: '<';
LTEQ: '<=';
GT: '>';
GTEQ: '>=';

AND: '&&';
OR: '||';

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';

Int: '\'' [0-9]+ '\'';
Float: '\'' [0-9]+ '.' [0-9]+ '\'';

String: '\'' ~('\r' | '\n' | '\'')* '\'';

AVG: 'AVG';
COUNT: 'COUNT';
MAX: 'MAX';
MIN: 'MIN';
SUM: 'SUM';
IMPLODE: 'IMPLODE';

ColName: [a-zA-Z0-9]+;
GenFunc: [a-zA-Z]+;


OPTWS: (' ' | '\t') -> skip;
WS: (' ' | '\t')+;
NL: '\r'? '\n';