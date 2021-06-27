with recursive stirling (n, k0, k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, k12) as
(
    select 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 
    union all
    select n+1, 0, n*k1+k0, n*k2+k1, n*k3+k2, n*k4+k3, n*k5+k4, n*k6+k5, n*k7+k6, n*k8+k7, n*k9+k8, n*k10+k9, n*k11+k10, n*k12+k11
    from stirling where n < 13   
)
select * from stirling;