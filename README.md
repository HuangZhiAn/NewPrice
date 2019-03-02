# NewPrice

区间更新算法。根据更新记录，得出整个时间线上，所有时间点的最新价格  
输入输出示例如下  
输入  

<style>table th:first-of-type { width: 300px;}</style>
|编号|开始日期|结束日期|价格|更新日期|  
|:----:|:----:|:----:|:----:|:----:| 
| 1 | 2018/1/1 | 2018/1/31 | 100 | 2018/1/1 |  
| 2 | 2018/1/2 | 2018/1/4 | 300 | 2018/1/2 |  
| 3 | 2018/1/5 | 2018/1/22 | 400 | 2018/1/4 |  
| 4 | 2018/1/20 | 2018/1/28 | 500 | 2018/1/15 |  
| 5 | 2018/2/5 | 2018/2/15 | 600 | 2018/2/1 |  
| 6 | 2018/2/15 | 2018/2/25 | 700 | 2018/2/1 |  

输出  

开始日期|结束日期|价格|  
|:----:|:----:|:----:|  
| 2018/1/1 | 2018/1/2 | 100 |  
| 2018/1/2 | 2018/1/4 | 300 |  
| 2018/1/4 | 2018/1/5 | 100 |  
| 2018/1/5 | 2018/1/20 | 400 |  
| 2018/1/20 | 2018/2/5 | 500 |  
| 2018/2/5 | 2018/2/15 | 600 |  
| 2018/2/15 | 2018/2/25 | 700 |  
| 2018/2/25 | 2018/2/28 | 500 |

每个有更新记录的时间区间都能找到最新的价格