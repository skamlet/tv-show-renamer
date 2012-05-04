srcdir := src
bindir := bin
objdir := $(bindir)/classes

packages := fr.skamlet.renamer

modules := $(subst .,/,$(packages))
srcsdir := $(srcdir)
srcsdir += $(addprefix $(srcdir)/, $(modules))

objsdir := $(objdir)
objsdir += $(addprefix $(objdir)/, $(modules))

srcs := $(foreach sdir, $(srcsdir), $(wildcard $(sdir)/*.java))
objs := $(patsubst $(srcdir)/%.java, $(objdir)/%.class, $(foreach sdir, $(srcsdir), $(wildcard $(sdir)/*.java)))

localdirs :=\
	$(bindir)\
	$(objsdir)

program_name := tv-show-renamer
mkdir := mkdir -p
javac := javac
jar := jar

program := $(bindir)/$(program_name).jar

all: $(localdirs) $(program)

clean:
	$(RM) -r $(localdirs)

$(program): $(objs)
	$(jar) cvf $@ -C $(objdir) .

$(objs): $(srcs)
	$(javac) -d $(objdir) $^

$(localdirs):
	$(mkdir) $@

