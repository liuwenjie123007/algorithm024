# 递归代码模板
```java
void recursion(int level, int param) {   
    recursion terminator  if (level > MAX_LEVEL) {     
        //process result    
        return;   
    }  
    //process current logic   
    process(level, param);  
    // drill down   
    recursion(level + 1, param);  
    //reverse the current level status if needed
}
```

# 分治代码模板
```java
private static int divide_conquer(Problem problem, ) {    
    if (problem == NULL) {    
        int res = process_last_result();    
        return res;       
    }  
    subProblems = split_problem(problem)    
    
    res0 = divide_conquer(subProblems[0])  
    res1 = divide_conquer(subProblems[1])    
    
    result = process_result(res0, res1);    
    return result;
}
```

# 回溯代码模板
```java
private void backtrace(State state, Data data) {
    if (state.isComplete()) {
        addStateToResult(state);
        return;
    }

    // loop all choice
    while (condition) {
        Data curentData = processData(data);
        state.changeState();
        backtrace(state, data);
        state.rollbackState();
    }
}
```

# 牛顿迭代法
```c++
int mysqrt(int x){
	double tmpx = x;
	double k = 1.0;
	double k0 = 0.0;
	while(abs(k0-k) >= 1){
		k0 = k;
		k = (k + tmpx/k)/2;
	}
	return (int)k;
}
```

## 体会：五毒神掌YYDS!

## 无法直观理解回溯 -> 五毒10+题 -> 自然回溯思考