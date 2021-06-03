# Annotation

## Types of Annotation

<h3>1 - Marker Annotation</h3>

An annotation that has no method, is called marker annotation.
<p>
<code> @interface MyAnnotation{}   </code>
</p>

<h3>2 - Single-Value Annotation</h3>
An annotation that has one method, is called single-value annotation
<p>
<code>
@interface MyAnnotation{  
int value();  
}
</code></p>
 <h3> 3 - Multi-Value Annotation</h3>
An annotation that has more than one method, is called Multi-Value annotation.
<p>
<code>
@interface MyAnnotation{  
int value1();  
String value2();  
String value3();  
}  
}
</code>
</p>
<p>
<code>
@interface MyAnnotation{  
int value1() default 1;  
String value2() default "";  
String value3() default "xyz";  
}  
</code>
</p>
