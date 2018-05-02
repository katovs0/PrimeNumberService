# PrimeNumberService

> curl localhost:6399/primes/100
[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97]

> curl -v -H "Accept: application/xml" localhost:6399/primes/100?algorithm=slow
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 6399 (#0)
> GET /primes/100?algorithm=slow HTTP/1.1
> Host: localhost:6399
> User-Agent: curl/7.54.0
> Accept: application/xml
>
< HTTP/1.1 200
< Content-Type: application/xml;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Tue, 01 May 2018 23:07:01 GMT
<
* Connection #0 to host localhost left intact
<PrimesResponse><initial>100</initial><primes><primes>2</primes><primes>3</primes><primes>5</primes><primes>7</primes><primes>11</primes><primes>13</primes><primes>17</primes><primes>19</primes><primes>23</primes><primes>29</primes><primes>31</primes><primes>37</primes><primes>41</primes><primes>43</primes><primes>47</primes><primes>53</primes><primes>59</primes><primes>61</primes><primes>67</primes><primes>71</primes><primes>73</primes><primes>79</primes><primes>83</primes><primes>89</primes><primes>97</primes></primes></PrimesResponse>


> curl -v -H "Accept: application/json" localhost:6399/primes/100?algorithm=slow
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 6399 (#0)
> GET /primes/100?algorithm=slow HTTP/1.1
> Host: localhost:6399
> User-Agent: curl/7.54.0
> Accept: application/json
>
< HTTP/1.1 200
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Tue, 01 May 2018 23:08:18 GMT
<
* Connection #0 to host localhost left intact
{"initial":100,"primes":[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97]}