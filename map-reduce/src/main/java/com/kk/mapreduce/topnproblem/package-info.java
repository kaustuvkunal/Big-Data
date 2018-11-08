
 
/**
 * Provide MapReduce classes to solve TopN problem 
 * <p>Given employee data set find 
 *  <p>  
 * a)Top N highest paid employees and <p>
 * b)Top N salaries
 * <p>
 * Here problem a & b are different. Top N highest paid employee may results into more than 
 * N employees because some employee can have identical salaries 
 * however top N salaries should emit N entries only.
 * <p>
 * The example can be modified and used in variety of topN problems like topN Twitter
 * Hashtags, topN ordered products,topN Urls hit, topN searches etc.   
 * <p>
 * @author Kaustuv Kunal
 * @since  30-Oct-2018 10:33:02 PM
 * @version 1.0
 */
package com.kk.mapreduce.topnproblem;