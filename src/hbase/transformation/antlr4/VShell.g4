grammar VShell;

/*
    Syntax rules for HBase Virtual Shell (VShell).

    Created by Sougata on 25 February 2015
*/

/*
@header {
    package hbase.transformation.antlr4;
}
*/

options {
    language = Java;
}

parse
 : vshell EOF
 ;

/*
vshell : operation (space)+ remString ;


remString :    (remString ',' (space)+ remString)
			 | (remString ',' (space)* remString ',' (space)* remString)
			 |  StringValue
			 ; 


vshell : operation=(scan) ((space)+ baseTable)                                        #scanOperation
       | operation=(get) ((space)+ baseTable ',' (space)* rowKey)                     #getOperation
       | operation=(define) ((space)+ virtualTable ',' baseTable ',' notaqlScript)       #defineOperation
       ;

baseTable : TableName;
virtualTable : TableName;
rowKey : TableName;
notaqlScript : StringValue;
*/
      


/*vshell : operation (space)+  expr  (',' (space)*  expr )* ;

expr : TableName;
*/




vshell : (space)? Scan (space)  baseTable ';'? (space)?                                                             #scanOperation
       | (space)? Get (space)  baseTable ',' (space)* rowKey ';'? (space)?                                          #getOperation
       | (space)? Define (space) virtualTable ',' (space)* baseTable ',' (space)* notaqlScript ';'? (space)?        #defineOperation
       | (space)? Drop (space)  baseTable ';'? (space)?                                                             #dropOperation
       | (space)? List (space)?                                                                                     #listOperation
       ;

Scan : 'scan' ;
Get  : 'get';
Define : 'define';
Drop : 'drop';
List : 'list';

baseTable : StringValue;
virtualTable : StringValue;
rowKey : StringValue;
notaqlScript : StringValue;


/* tablename and rowkey values can be given with either single or double quotes */


//TableName : ('\'' | '"') [a-zA-Z0-9_]+ ('\'' | '"');
//StringValue: ('"') [a-zA-Z0-9.><!:;_=,'\- ]+ ('"');
StringValue : '"' (~[\r\n"])+ '"'
    {
     String s = getText();
     s = s.substring(1, s.length() - 1); // strip the leading and trailing quotes
     s = s.replace("\"\"", "\"");       // replace all double quotes with single quotes
     setText(s);
   }
   ;


space: (' ' | '\t' | '\r')+ ;
WS : [ ' '\s\t\r\n]+ -> skip ;