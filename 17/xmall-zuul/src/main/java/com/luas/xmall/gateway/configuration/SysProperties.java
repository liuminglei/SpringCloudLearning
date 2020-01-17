package com.luas.xmall.gateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties(prefix = "sys.zuul")
public class SysProperties {

    private PreAuthenticationFilter preAuthenticationFilter = new PreAuthenticationFilter();

    public PreAuthenticationFilter getPreAuthenticationFilter() {
        return preAuthenticationFilter;
    }

    public void setPreAuthenticationFilter(PreAuthenticationFilter preAuthenticationFilter) {
        this.preAuthenticationFilter = preAuthenticationFilter;
    }

    public static class PreAuthenticationFilter {

        private boolean enabled = true;

        private int order = 0;

        private Set<String> ignoreAntPatterns;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public Set<String> getIgnoreAntPatterns() {
            return ignoreAntPatterns;
        }

        public void setIgnoreAntPatterns(Set<String> ignoreAntPatterns) {
            this.ignoreAntPatterns = ignoreAntPatterns;
        }
    }

}
