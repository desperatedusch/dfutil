--Check database before and after successionhandlings
SELECT uuid
FROM public.ort
where already_applied_at is not null
   or outdated_at is not null
UNION
SELECT uuid
FROM public.ortsteil
where already_applied_at is not null
   or outdated_at is not null
UNION
SELECT uuid
FROM public.strasse
where already_applied_at is not null
   or outdated_at is not null;

